package tn.esprit.picloundomicsgestionagriculteur.auth;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

//    // Compile the regular expression pattern
    Pattern pattern = Pattern.compile(regex);
//
//    // Match the input string against the pattern
    Matcher matcher = pattern.matcher(s);
//
//    // Return true if the input string matches the pattern, false otherwise
    return matcher.matches();
    }

}