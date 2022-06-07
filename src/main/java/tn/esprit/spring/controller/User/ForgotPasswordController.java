package tn.esprit.spring.controller.User;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Exception.UserNotFoundException;
import Utils.Utility;
import net.bytebuddy.utility.RandomString;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.payload.request.ResetPaswword;
import tn.esprit.spring.service.User.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
@Controller
public class ForgotPasswordController {
	
	
	@Autowired
	private UserService userService;
	
	private JavaMailSender javaMailSender;
	
	 @Autowired
	    private JavaMailSender mailSender;
	
	@GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
		 return "forgot_password_form";
 
    }
 
	@PostMapping(value = "/forgot_password/{email}")
	public String processForgotPassword(@PathVariable(value = "email", required = true) String email, Model model) {
	    String token = RandomString.make(30);
	     
	    try {
	    	userService.updateResetPasswordToken(token, email);
	        String resetPasswordLink = "http://localhost:3000"+ "/reset_password?token=" + token;
	        sendEmail(email, resetPasswordLink);
	        model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
	         
	    } catch (UserNotFoundException ex) {
	        model.addAttribute("error", ex.getMessage());
	    } catch (UnsupportedEncodingException | MessagingException e) {
	        model.addAttribute("error", "Error while sending email");
	    }
	         
	    return "forgot_password_form";
	}
	
	public void sendEmail(String recipientEmail, String link)
	        throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		   MimeMessageHelper helper = new MimeMessageHelper(message, true);
	     
		   helper.setFrom("jawherbahrouni06@gmail.com");
		   helper.setTo(recipientEmail);
	     
	    String subject = "Here's the link to reset your password";
	     
	    String content = "<p>Hello,</p>"
	            + "<p>You have requested to reset your password.</p>"
	            + "<p>Click the link below to change your password:</p>"
	            + "<p><a href=\"" + link + "\">Change my password</a></p>"
	            + "<br>"
	            + "<p>Ignore this email if you do remember your password, "
	            + "or you have not made the request.</p>";
	     
	    helper.setSubject(subject);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	}
     
	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
	    User user = userService.getByResetPasswordToken(token);
	    model.addAttribute("token", token);
	     
	    if (user == null) {
	        model.addAttribute("message", "Invalid Token");
	        return "message";
	    }
	     
	    return "reset_password_form";
	} 
	
	@PostMapping("/reset_password")
	public String processResetPassword(@RequestBody ResetPaswword reset, Model model) {
	    String token = reset.getToken();
	    String password = reset.getPassword();
	    System.out.println(token);
	    System.out.println(password);

	     
	    User user = userService.getByResetPasswordToken(token);
	    model.addAttribute("title", "Reset your password");
	     
	    if (user == null) {
	        model.addAttribute("message", "Invalid Token");
	        return "No user";
	    } else {           
	    	userService.updatePassword(user, password);
	         
	        model.addAttribute("message", "You have successfully changed your password.");
	    }

	     
	    return "message";
	}
   
}
