package kz.bitlab.middle01reids.middle01reids.api;

import jakarta.servlet.http.HttpSession;
import kz.bitlab.middle01reids.middle01reids.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class SessionController {

    @GetMapping
    public String welcomePage() {
        return "Welcome Page";
    }

    @GetMapping(value = "/session")
    public String getSession(HttpSession session) {
        return "session : " + session;
    }

    @GetMapping(value = "/setup/{name}")
    public String setup(@PathVariable(name = "name") String name, HttpSession session) {
        session.setAttribute("userName", name);
        return "saved";
    }

    @GetMapping(value = "/current-user")
    public String getCurrentUser(HttpSession session) {
        return (String) session.getAttribute("userName");
    }

    @PostMapping(value = "/add-item")
    public String addItem(@RequestBody Product product, HttpSession session) {
        ArrayList<Product> productList;
        Object sessionObject = session.getAttribute("products");
        if (sessionObject == null) {
            productList = new ArrayList<>();
        } else {
            productList = (ArrayList<Product>) sessionObject;
        }
        productList.add(product);
        session.setAttribute("products", productList);
        return "saved";
    }

    @GetMapping(value = "/list-item")
    public ArrayList<Product> listItems(HttpSession session) {
        ArrayList<Product> productList;
        Object sessionObject = session.getAttribute("products");
        if (sessionObject == null) {
            productList = new ArrayList<>();
        } else {
            productList = (ArrayList<Product>) sessionObject;
        }
        return productList;
    }
}
