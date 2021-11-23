package by.miashkou.dc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class MainController {

    List<Map<String, String>> messages = new ArrayList<>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "First message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "Second message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "Third message");
        }});
    }};
    int counter = 4;


    @RequestMapping(method = RequestMethod.GET, path = "hello")
    public ResponseEntity<List<Map<String, String>>> hello() {
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "post")
    public ResponseEntity<List<Map<String, String>>> getAll() {
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "post/{id}")
    public ResponseEntity<Map<String, String>> getById(@PathVariable int id) {
        return new ResponseEntity<>(messages.get(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "post/{id}")
    public ResponseEntity<List<Map<String, String>>> delete(@PathVariable int id) {
        for (Map<String, String> message : messages) {
            if (Integer.parseInt(message.get("id")) == id) {
                messages.remove(message);
                break;
            }
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "post/{id}")
    public ResponseEntity<List<Map<String, String>>> put(@PathVariable int id, @RequestBody Map<String, String> request) {
        messages.set(id, request);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "post")
    public ResponseEntity<Map<String, String>> postById(@RequestBody Map<String, String> request) {
        request.put("id", String.valueOf(counter));
        counter++;
        messages.add(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}
