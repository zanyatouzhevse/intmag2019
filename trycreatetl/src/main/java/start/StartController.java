package start;
import javafx.scene.web.WebView;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.resource.HttpResource;
import org.thymeleaf.engine.HTMLElementName;
import start.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import start.domain.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.ArrayList;

@Controller

@SessionAttributes("basket")
public class StartController {

    @Autowired
    UserRepository itemRepository;

    public @ModelAttribute ("basket")
    Corzina createBas(){
        return new Corzina();
    }


    @GetMapping("/greeting")

    public String greeting(Model model, @ModelAttribute(value="basket") Corzina basket) {
        Iterable<Item> getAll =  itemRepository.findAll();
        model.addAttribute("corz", basket);
        model.addAttribute("list",getAll);
        return "greeting";
    }

    @PostMapping("/addtovar") // Map ONLY POST Requests
    public @ResponseBody
     ModelAndView addNewItem (@RequestParam String name
            , @RequestParam int cost, @RequestParam String description) {
        Item tov = new Item();
        tov.setName(name);
        tov.setCost(cost);
        tov.setDescription(description);
        itemRepository.save(tov);
        return perehodNaAdd();
    }
    @RequestMapping("/addtovar")
    public ModelAndView perehodNaAdd() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/addtovarcomplite");
        return modelAndView;
    }
    @GetMapping("/addtovar")
    public void addtovar(Model model) {
return;
    }
    @GetMapping("/addtovarcomplite")
    public void addtovarcomplite(Model model) {
        return;
    }

    @GetMapping("/updatetovar")
    public String updatetovar(@RequestParam int id, Model model) {
        Item uptovar=itemRepository.findById(id);
        model.addAttribute("upname", uptovar.getName());
        model.addAttribute("updescription", uptovar.getDescription());
        model.addAttribute("upcost", uptovar.getCost());


        return "updatetovar";
    }

    @PostMapping("/deletetovar")
    public @ResponseBody
    ModelAndView deleteItemByName (@RequestParam String name ) {

        itemRepository.deleteByName(name);
        return perehodNaAdd();
    }

    @GetMapping("/deletetovar")
    public String deletetovar(Model model) {
        Iterable<Item> getAll =  itemRepository.findAll();
        model.addAttribute("list",getAll);
        return "deletetovar";
    }


    @PostMapping("/updatetovar")
    public @ResponseBody
    ModelAndView updateItem (@RequestParam int id, @RequestParam String name, @RequestParam String description,
                             @RequestParam int cost ) {
        Item itForUp = itemRepository.findById(id);
        itForUp.setName(name);
        itForUp.setDescription(description);
        itForUp.setCost(cost);
        itemRepository.save(itForUp);
        return perehodNaAdd();
    }
    @PostMapping("/greeting")
    public @ResponseBody
    ModelAndView greet (@RequestParam int id ) {
        itemRepository.deleteById(id);
        return perehodNaAdd();
    }
    @GetMapping("/greeting/CrCl")
    public @ResponseBody
    ModelAndView CleanCor(@ModelAttribute(value="basket") Corzina basket){
      basket.clearAll();
      return perehodNaAdd();
    }


    @GetMapping("/greeting/db")
    public @ResponseBody
    ModelAndView dob (@ModelAttribute(value="basket") Corzina basket, int idT, String nameT, int costT) {
        ElementCorzini el=new ElementCorzini(idT, nameT, costT);
        basket.addEl(el);

       // ModelAndView modelAndView = new ModelAndView();
      //  modelAndView.setViewName("/addtovarcomplite");
        return perehodNaAdd();
        //redirect(http заголовок)
    }

    @GetMapping("/greeting/rm")
    public @ResponseBody
    ModelAndView greetRm (@RequestParam int id ) {
        itemRepository.deleteById(id);
        return perehodNaAdd();
    }






    @GetMapping("/index")
    void ind (HttpSession sess) {
        sess.setAttribute("basket", new Corzina());
      // createBas();
     return;
        }




}
