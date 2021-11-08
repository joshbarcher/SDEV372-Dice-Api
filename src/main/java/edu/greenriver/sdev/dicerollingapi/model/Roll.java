package edu.greenriver.sdev.dicerollingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roll
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollId;

    private int result;

    @ManyToOne
    @JoinColumn(name="diceId")
    @JsonIgnore
    private Dice dice;

    public Roll(int result, Dice dice)
    {
        this.result = result;
        dice.rollDice(this);
    }
}
