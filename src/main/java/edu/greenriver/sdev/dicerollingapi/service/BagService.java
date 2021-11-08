package edu.greenriver.sdev.dicerollingapi.service;

import edu.greenriver.sdev.dicerollingapi.model.Bag;
import edu.greenriver.sdev.dicerollingapi.model.Dice;
import edu.greenriver.sdev.dicerollingapi.repositories.IBagRepository;
import edu.greenriver.sdev.dicerollingapi.repositories.IDiceRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BagService
{
    private IBagRepository bagRepository;
    private IDiceRepository diceRepository;

    public BagService(IBagRepository bagRepository,
                      IDiceRepository diceRepository)
    {
        this.bagRepository = bagRepository;
        this.diceRepository = diceRepository;
    }

    //GET
    public List<Bag> allBags()
    {
        return bagRepository.findAll();
    }

    public Bag getById(int id)
    {
        return bagRepository.findById(id).orElseThrow();
    }

    //POST
    public Bag createBag(Bag bag)
    {
        return bagRepository.save(bag);
    }

    public Bag addToBag(int bagId, int diceId)
    {
        Bag bag = bagRepository.findById(bagId).orElseThrow();
        Dice dice = diceRepository.findById(diceId).orElseThrow();

        if (bag.getDiceInBag().size() == bag.getMaxCapacity())
        {
            throw new IllegalStateException("Bag is full.");
        }

        bag.addDiceToBag(dice);
        return bagRepository.save(bag);
    }

    public List<Dice> getDiceInBag(int bagId)
    {
        Bag bag = bagRepository.findById(bagId).orElseThrow();
        return bag.getDiceInBag();
    }

    //PUT
    public Bag updateBag(Bag updatedBag)
    {
        Bag bag = bagRepository.findById(updatedBag.getBagId()).orElseThrow();
        bag.setMaterials(updatedBag.getMaterials());
        bag.setMaxCapacity(updatedBag.getMaxCapacity());
        bag.setDiceInBag(updatedBag.getDiceInBag());
        return bagRepository.save(bag);
    }

    //DELETE
    public void deleteBag(int id)
    {
        //make sure the record is there first
        bagRepository.findById(id).orElseThrow();
        bagRepository.deleteById(id);
    }
}
