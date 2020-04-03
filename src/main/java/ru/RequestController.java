package ru;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import models.Dialog;
import models.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class RequestController {
    private final Gson gson = new Gson();
    private final JsonParser jsonParser = new JsonParser();
    RequestHandler requestHandler = new RequestHandler();

    @GetMapping("/")
    public String hello() {
        return ""+new Date();
    }


    @GetMapping("/status")
    public List<Status> deliveredStatus() {
        return Arrays.asList(new Status(ThreadLocalRandom.current().nextBoolean(), ThreadLocalRandom.current().nextInt()));
    }

    @PostMapping("/create")
    public String create(@RequestBody String body) {

        return jsonParser.parse(body).getAsJsonObject().get("PREFIX").getAsString();
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody String body){

        ServerResponse response = RequestHandler.register(gson.fromJson(body, RegisterForm.class));

        return gson.toJson(response);
    }

    @PostMapping("/sendmessage")
    public String sendMessage(@RequestBody String body){

        ServerResponse response = RequestHandler.sendMessage(gson.fromJson(body, MessageForm.class));

        return gson.toJson(response);
    }

    @PostMapping("/createdialog")
    public String createDialog(@RequestBody String body){

        ServerResponse response = RequestHandler.createDialog(gson.fromJson(body, DialogForm.class));

        return gson.toJson(response);
    }

    @GetMapping("getmessages")
    public String getMessages(@RequestBody String body){

        ArrayList<Message> messages = RequestHandler.getMessages(gson.fromJson(body, DialogForm.class));

        if (messages != null) return gson.toJson(messages);
        else return gson.toJson(new ServerResponse("get messages", "no", "401", "bad database connection"));
    }

    @GetMapping("getdialogs")
    public String getDialogs(@RequestBody String body){

        ArrayList<Dialog> dialogs = RequestHandler.getDialogs(jsonParser.parse(body).getAsJsonObject().get("user").getAsString());

        return gson.toJson(dialogs);
    }

    @PostMapping("login")
    public String login(@RequestBody String body){

        return gson.toJson(RequestHandler.login(jsonParser.parse(body).getAsJsonObject().get("user").getAsString()));
    }
}
