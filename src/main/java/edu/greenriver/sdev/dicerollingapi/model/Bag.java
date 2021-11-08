package edu.greenriver.sdev.dicerollingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bag
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bagId;

    private String materials;
    private int maxCapacity;

    @OneToMany(mappedBy = "bag")
    private List<Dice> diceInBag;

    public Bag(String materials, int maxCapacity)
    {
        this.materials = materials;
        this.maxCapacity = maxCapacity;
    }

    public void addDiceToBag(Dice dice)
    {
        //make sure the list is there
        if (diceInBag == null)
        {
            diceInBag = new ArrayList<>();
        }

        //connect dice and bag
        diceInBag.add(dice);
        dice.setBag(this);
    }
}
