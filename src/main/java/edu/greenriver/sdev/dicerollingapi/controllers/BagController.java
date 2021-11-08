package edu.greenriver.sdev.dicerollingapi.controllers;

import edu.greenriver.sdev.dicerollingapi.model.Bag;
import edu.greenriver.sdev.dicerollingapi.model.Dice;
import edu.greenriver.sdev.dicerollingapi.model.Roll;
import edu.greenriver.sdev.dicerollingapi.service.BagService;
import edu.greenriver.sdev.dicerollingapi.service.DiceService;
import edu.greenriver.sdev.dicerollingapi.service.RollService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("bags")
public class BagController
{
    private BagService bagService;
    private DiceService diceService;
    private RollService rollService;

    public BagController(BagService bagService, DiceService diceService,
                         RollService rollService)
    {
        this.bagService = bagService;
        this.diceService = diceService;
        this.rollService = rollService;
    }

    @GetMapping
    public ResponseEntity<List<Bag>> allBags()
    {
        return new ResponseEntity<>(bagService.allBags(), HttpStatus.OK);
    }

    @GetMapping("{bagId}")
    public ResponseEntity<Bag> bagById(@PathVariable int bagId)
    {
        return new ResponseEntity<>(bagService.getById(bagId), HttpStatus.OK);
    }

    @GetMapping("{bagId}/dice/")
    public ResponseEntity<List<Dice>> getDiceInBag(@PathVariable int bagId)
    {
        return new ResponseEntity<>(bagService.getDiceInBag(bagId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Bag> createBag(@RequestBody Bag bag)
    {
        return new ResponseEntity<>(bagService.createBag(bag), HttpStatus.CREATED);
    }

    @PostMapping("{bagId}/dice/{diceId}")
    public ResponseEntity<Bag> addDiceToBag(@PathVariable int bagId, @PathVariable int diceId)
    {
        Bag bag = bagService.getById(bagId);
        Dice dice = diceService.getById(diceId);
        bag.addDiceToBag(dice);
        return new ResponseEntity<>(bagService.updateBag(bag), HttpStatus.CREATED);
    }

    @PostMapping("{bagId}/roll")
    public ResponseEntity<List<Roll>> rollDiceInBag(@PathVariable int bagId)
    {
        Bag bag = bagService.getById(bagId);
        List<Dice> diceList = bag.getDiceInBag();
        List<Roll> rolls = new ArrayList<>();

        for (int i = 0; i < diceList.size(); i++)
        {
            Dice dice = diceList.get(i);
            Roll roll = rollService.createRoll(dice.getDiceId());
            dice.rollDice(roll);
            rolls.add(roll);
        }

        if (rolls.size() > 0)
        {
            return new ResponseEntity<>(rolls, HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping
    public ResponseEntity<Bag> updateBag(@RequestBody Bag bag)
    {
        return new ResponseEntity<>(bagService.updateBag(bag), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{bagId}")
    public ResponseEntity<Bag> updateBag(@PathVariable int bagId)
    {
        bagService.deleteBag(bagId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*@ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleMissingElement()
    {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/
}
