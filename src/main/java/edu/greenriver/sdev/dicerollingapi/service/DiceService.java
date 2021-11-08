package edu.greenriver.sdev.dicerollingapi.service;

import edu.greenriver.sdev.dicerollingapi.model.Dice;
import edu.greenriver.sdev.dicerollingapi.repositories.IDiceRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiceService
{
    private IDiceRepository repo;

    public DiceService(IDiceRepository repo)
    {
        this.repo = repo;
    }

    //GET
    public List<Dice> allDice()
    {
        return repo.findAll();
    }

    public Dice getById(int id)
    {
        return repo.findById(id).orElseThrow();
    }

    //POST
    public Dice createDice(Dice dice)
    {
        return repo.save(dice);
    }

    //PUT
    public Dice updateDice(int id, Dice updatedDice)
    {
        Dice existingDice = repo.findById(id).orElseThrow();
        existingDice.setColor(updatedDice.getColor());
        existingDice.setSides(updatedDice.getSides());
        return existingDice;
    }

    //DELETE
    public void deleteDice(int id)
    {
        //make sure the record is there first
        repo.findById(id).orElseThrow();
        repo.deleteById(id);
    }
}
