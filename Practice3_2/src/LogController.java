@Controller
public class LogController {

    private final String login = "sema";
    private final String password = "qwerty";
    private long startTime = 0L;

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/log";
    }

    @GetMapping("/log")
    public ModelAndView loginForm(@RequestParam(value = "login", required = false, defaultValue = "defaultLogin") String login,
                                  @RequestParam(value = "password", required = false, defaultValue = "defaultPassword") String password) {
        ModelAndView modelAndView = new ModelAndView("log");

        if (login.equals("defaultLogin")) {
            return modelAndView;
        }

        if (startTime > System.currentTimeMillis()) {
            modelAndView.addObject("message",
                    "Please wait " + ((startTime - System.currentTimeMillis()) / (1000 * 60))
                            + " minutes before trying again.");
            return modelAndView;
        }

        boolean validCredentials = login.equals(this.login) && password.equals(this.password);

        if (validCredentials) {
            modelAndView.addObject("message", "Welcome back, " + login + "!");
        } else {
            startTime = System.currentTimeMillis() + 1000 * 60 * 15;
            modelAndView.addObject("message", "Invalid login or password!");
        }

        return modelAndView;
    }
}
