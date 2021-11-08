package edu.greenriver.sdev.dicerollingapi.controllers;

import edu.greenriver.sdev.dicerollingapi.model.Dice;
import edu.greenriver.sdev.dicerollingapi.service.DiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("dice")
public class DiceController
{
    private DiceService diceService;

    public DiceController(DiceService diceService)
    {
        this.diceService = diceService;
    }

    @GetMapping
    public ResponseEntity<List<Dice>> allDice()
    {
        return new ResponseEntity<>(diceService.allDice(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Dice> diceById(@PathVariable int id)
    {
        return new ResponseEntity<>(diceService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addDice(@RequestBody Dice dice)
    {
        return new ResponseEntity(diceService.createDice(dice), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateDice(@RequestBody Dice dice)
    {
        return new ResponseEntity(diceService.createDice(dice), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteDice(@PathVariable int id)
    {
        diceService.deleteDice(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /*@ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleMissingElement()
    {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/
}
