package tn.esprit.picloundomicsgestionagriculteur.RestController.UserController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionUser.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tn.esprit.picloundomicsgestionagriculteur.Config.LogoutService;
import tn.esprit.picloundomicsgestionagriculteur.Repository.UserRepo.UserRepository;
import tn.esprit.picloundomicsgestionagriculteur.Service.UserService.IUser;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")

public class UserController {


    IUser us ;
    LogoutService logoutService;
    UserRepository rs;


    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }


    @PostMapping ("/Admin/ADD")
    @PreAuthorize("hasAuthority('admin')")
    public User addUserA(@RequestBody User user){
        return us.addUser(user);

    }

    //données inserées champ par champ: @RequestBody

    @PutMapping("/updateUser/{id}")
    @PreAuthorize("hasAnyAuthority('admin','agriculteur','client')")
    public User updateUserA(@PathVariable("id")  Integer id, @RequestBody User user){
          rs.findById(id);
        return us.updateUser(id,user);

    }
    @GetMapping("/getById/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public Optional<User> retrieveUserById(@PathVariable("id")Integer id){
        return us.retrieveUserById(id);
    }

    @GetMapping("/getAllUser")
    @PreAuthorize("hasAuthority('admin')")
    public List<User> retrieveAllUsers(){
        return us.retrieveAllUsers();

    }
    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasAnyAuthority('admin','agriculteur')")
    public void deleteUser(@PathVariable("id") Integer id){
        us.deleteUser(id);

    }


    @GetMapping("/profile")
    public User Profile (Principal principal) {

        return us.loadUserByUsername(principal.getName());
    }





}