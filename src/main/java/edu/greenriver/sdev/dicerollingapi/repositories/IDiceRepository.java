package edu.greenriver.sdev.dicerollingapi.repositories;

import edu.greenriver.sdev.dicerollingapi.model.Dice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDiceRepository extends JpaRepository<Dice, Integer>
{
}
