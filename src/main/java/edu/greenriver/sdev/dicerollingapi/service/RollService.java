package edu.greenriver.sdev.dicerollingapi.service;

import edu.greenriver.sdev.dicerollingapi.model.Bag;
import edu.greenriver.sdev.dicerollingapi.model.Dice;
import edu.greenriver.sdev.dicerollingapi.model.Roll;
import edu.greenriver.sdev.dicerollingapi.repositories.IDiceRepository;
import edu.greenriver.sdev.dicerollingapi.repositories.IRollRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RollService
{
    private Random random = new Random();
    private IRollRepository rollRepository;
    private IDiceRepository diceRepository;

    public RollService(IRollRepository rollRepository,
                       IDiceRepository diceRepository)
    {
        this.rollRepository = rollRepository;
        this.diceRepository = diceRepository;
    }

    //GET
    public List<Roll> allDice()
    {
        return rollRepository.findAll();
    }

    public Roll getById(int id)
    {
        return rollRepository.findById(id).orElseThrow();
    }

    //POST
    public Roll createRoll(int diceId)
    {
        Dice dice = diceRepository.findById(diceId).orElseThrow();
        int diceRoll = random.nextInt(dice.getSides()) + 1;
        return rollRepository.save(new Roll(diceRoll, dice));
    }

    public List<Roll> getDiceRolls(int diceId)
    {
        Dice dice = diceRepository.findById(diceId).orElseThrow();
        return dice.getRolls();
    }

    //PUT
    public Roll updateRoll(Roll updatedRoll)
    {
        Roll roll = rollRepository.findById(updatedRoll.getRollId()).orElseThrow();
        roll.setResult(updatedRoll.getResult());
        return rollRepository.save(roll);
    }

    //DELETE
    public void deleteRoll(int id)
    {
        //make sure the record is there first
        rollRepository.findById(id).orElseThrow();
        rollRepository.deleteById(id);
    }
}
