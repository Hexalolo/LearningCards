package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Controller
public class CardController {

    private List<Card> listOfCards;

    public CardController() {
        Card card1 = new Card("Example", "Przykład");
        Card card2 = new Card("For instance", "Dla przykładu");
        Card card3 = new Card("Trial", "Próba");
        listOfCards = new ArrayList<>();
        listOfCards.add(card1);
        listOfCards.add(card2);
        listOfCards.add(card3);

    }

    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("newCard", new Card());
        return "add";
    }

    @PostMapping("/addcards")
    public String addPost(@ModelAttribute Card newCard){
        listOfCards.add(newCard);
        return "success";
    }


    @GetMapping("/success")
    public String success(){
        return "success";
    }

    @GetMapping("/draw")
    public String draw(Model model){
        int randomCardNumber = (int) (Math.random() * listOfCards.size());
        model.addAttribute("randomCard", listOfCards.get(randomCardNumber));
        return "draw";
    }

    @GetMapping("/all")
    public String all(Model model){
        model.addAttribute("list", listOfCards);
        return "all";
    }



    @GetMapping("/delete")
    public String delete(Model model){
        model.addAttribute("selectedCard", new Card());
        model.addAttribute("list", listOfCards);
        return "delete";
    }

    @PostMapping("/deletecards")
    public String deletePost(@ModelAttribute Card selectedCard){
        String searchingValue = selectedCard.getField1().substring(13,selectedCard.getField1().length()-2);
        ListIterator<Card> iter = listOfCards.listIterator();
        while(iter.hasNext()){
            if(iter.next().getField1().equals(searchingValue)){
                iter.remove();
            }
        }
        return "main";
    }


}
