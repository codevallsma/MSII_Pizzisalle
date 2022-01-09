package Utils;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvBuilder;

//We will use the singleton method because we can only create passwords once
public class Passwords {
    private Dotenv dotenv = null;
    private static final Passwords INSTANCE = new Passwords();

    private Passwords(){
        DotenvBuilder dotenvBuilder = Dotenv.configure()
                .directory("assets")
                .filename(".env");
        this.dotenv = dotenvBuilder.load();
    }
    public static Passwords getInstance(){
        return INSTANCE;
    }

    public String getEnvVariablesByType(String passType){
        return this.dotenv.get(passType);
    }
}
