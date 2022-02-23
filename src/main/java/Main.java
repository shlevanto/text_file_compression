import picocli.CommandLine;

import config.Config;

/**
 * The application doesn't have UI yet, so it is run from the Class at the moment.
 */
public class Main {

    public static void main(String[] args) {
        Config config = new Config();
        CommandLine.run(new Cli(config), args);
    }
}