package com.heliverse.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heliverse.entities.Customer;
import com.heliverse.entities.ERole;
import com.heliverse.entities.EmailDetails;
import com.heliverse.entities.OTP;
import com.heliverse.entities.Role;
import com.heliverse.exception.EmailException;
import com.heliverse.payload.JwtResponse;
import com.heliverse.payload.LoginRequest;
import com.heliverse.payload.MessageResponse;
import com.heliverse.payload.SignupRequest;
import com.heliverse.repo.CustomerRepo;
import com.heliverse.repo.RoleRepo;
import com.heliverse.security.JwtUtils;
import com.heliverse.security.UserDetailsImpl;
import com.heliverse.service.EmailService;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	  @Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  CustomerRepo userRepository;

	  @Autowired
	  RoleRepo roleRepository;

	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtUtils jwtUtils;
	  
	  private SignupRequest signUpRequest;
	  
	  @Autowired 
	    private EmailService emailService;
	  
	  private String msgBody;

	  @PostMapping("/login")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
	    List<String> roles = userDetails.getAuthorities().stream()
	        .map(item -> item.getAuthority())
	        .collect(Collectors.toList());

	    return ResponseEntity.ok(new JwtResponse(jwt, 
	                         userDetails.getId(), 
	                         userDetails.getUsername(), 
	                         userDetails.getEmail(), 
	                         roles));
	  }
	  
	  
	  
	  @PostMapping("/verify")
	  public ResponseEntity<?> verifyEmail(@RequestBody OTP otp) throws EmailException
	  {
		  if(otp.getOtp().equals(msgBody))
		  {
			  
			  // Create new user's account
			    Customer user = new Customer(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getDob(), signUpRequest.getPhone(), encoder.encode(signUpRequest.getPassword()));

			    Set<String> strRoles = signUpRequest.getRoles();
			    Set<Role> roles = new HashSet<>();

			    if (strRoles == null) {
			      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
			          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			      roles.add(userRole);
			    } else {
			      strRoles.forEach(role -> {
			        switch (role) {
			        case "admin":
			          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
			              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			          roles.add(adminRole);

			          break;
			        case "creator":
			          Role modRole = roleRepository.findByName(ERole.ROLE_CREATOR)
			              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			          roles.add(modRole);

			          break;
			        default:
			          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
			              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			          roles.add(userRole);
			        }
			      });
			    }
			    
			    user.setRoles(roles);
			    userRepository.save(user);
			  
		  }
		  else
		  {
			  throw new EmailException("OTP is wrong , please verify it again");
		  }
		  
		  return ResponseEntity.ok(new MessageResponse("Signup Successfull now you can login"));
		  
	  }
	  
	  

	  @PostMapping("/signup")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest surequest) {

	    if (userRepository.existsByEmail(surequest.getEmail())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Email is already in use!"));
	    }
	    
	    String recipient=surequest.getEmail();
	    
	    String numbers = "0123456789";  
	    Random rndm_method = new Random();  
	    char[] otp = new char[6];  
	    for (int i = 0; i < 6; i++) {  
	        otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));  
	    }  
	    
	     msgBody=new String(otp);
	     
	    String subject="OTP";
	    signUpRequest=surequest;
	    EmailDetails emd=new EmailDetails(recipient,msgBody,subject);
	    emailService.sendSimpleMail(emd);

	   
	    return ResponseEntity.ok(new MessageResponse("OTP is sent on Email please check and verify it"));
	  }
	  
	 
	
	

}
