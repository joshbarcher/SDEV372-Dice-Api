package edu.greenriver.sdev.dicerollingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Dice
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diceId;

    private String color;
    private int sides;

    @OneToMany(mappedBy = "dice")
    @JsonIgnore
    private List<Roll> rolls;

    @ManyToOne
    @JoinColumn(name = "bagId")
    @JsonIgnore
    private Bag bag;

    public Dice(String color, int sides)
    {
        this.color = color;
        this.sides = sides;
    }

    public void rollDice(Roll roll)
    {
        //make sure the list is there
        if (rolls == null)
        {
            rolls = new ArrayList<>();
        }

        //connect dice and bag
        rolls.add(roll);
        roll.setDice(this);
    }
}
