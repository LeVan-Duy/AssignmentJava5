//package com.leduy.backend.infrastructure.security.auth.service;
//
//import com.leduy.backend.entity.Employee;
//import com.leduy.backend.infrastructure.constant.Role;
//import com.leduy.backend.infrastructure.security.auth.AuthenticationResponse;
//import com.leduy.backend.infrastructure.security.auth.request.AuthenticationRequest;
//import com.leduy.backend.infrastructure.security.auth.request.RegisterRequest;
//import com.leduy.backend.infrastructure.security.config.JwtService;
//import com.leduy.backend.repository.EmployeeRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//    private final EmployeeRepository employeeRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthenticationResponse register(RegisterRequest request) {
//        var employee = Employee.builder()
//                .address(request.getAddress())
//                .email(request.getEmail())
//                .gender(request.getGender())
//                .fullName(request.getFullName())
//                .passWord(passwordEncoder.encode(request.getPassWord()))
//                .role(Role.USER)
//                .build();
//        employeeRepository.save(employee);
//        var jwtToken = jwtService.generateToken(employee);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//
//    }
//
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassWord()
//                )
//        );
//        var employee = employeeRepository.findByEmail(request.getEmail())
//                .orElseThrow();
//        System.out.println("============="+employee.getEmail());
//        var jwtToken = jwtService.generateToken(employee);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//}
