package edu.greenriver.sdev.dicerollingapi.repositories;

import edu.greenriver.sdev.dicerollingapi.model.Bag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBagRepository extends JpaRepository<Bag, Integer>
{
}
