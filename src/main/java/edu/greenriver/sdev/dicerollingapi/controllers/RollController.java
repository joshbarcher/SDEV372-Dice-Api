package edu.greenriver.sdev.dicerollingapi.controllers;

import edu.greenriver.sdev.dicerollingapi.model.Roll;
import edu.greenriver.sdev.dicerollingapi.service.RollService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("rolls")
public class RollController
{
    private RollService service;

    public RollController(RollService service)
    {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Roll>> allRolls()
    {
        return new ResponseEntity<>(service.allDice(), HttpStatus.OK);
    }

    @GetMapping("{diceId}")
    public ResponseEntity<List<Roll>> diceRolls(@PathVariable int diceId)
    {
        return new ResponseEntity<>(service.getDiceRolls(diceId), HttpStatus.OK);
    }

    @PostMapping("{diceId}")
    public ResponseEntity<Roll> rollDice(@PathVariable int diceId)
    {
        return new ResponseEntity<>(service.createRoll(diceId), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Roll> editRoll(@RequestBody Roll updatedRoll)
    {
        return new ResponseEntity<>(service.updateRoll(updatedRoll), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{rollId}")
    public ResponseEntity editRoll(@PathVariable int rollId)
    {
        service.deleteRoll(rollId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*@ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleMissingElement()
    {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/
}




