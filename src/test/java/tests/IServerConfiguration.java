package tests;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configuration.properties")
public interface IServerConfiguration extends Config {

        @Key("url")
        String url();
        @Key("login")
        String login();
        @Key("password")
        String password();
 }
