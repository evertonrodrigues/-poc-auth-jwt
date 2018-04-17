package com.poc.authjwt.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class ApplicationUserController {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser applicationUser){
        Assert.notNull(applicationUser.getUsername(), "ApplicationUser name cannot be null.");
        Assert.notNull(applicationUser.getPassword(), "ApplicationUser password cannot be null");
        applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        applicationUserRepository.save(applicationUser);

    }

    @GetMapping("/{username}")
    public ApplicationUser getUser(@PathVariable String username){
        return applicationUserRepository.findByUsername(username);
    }

}
