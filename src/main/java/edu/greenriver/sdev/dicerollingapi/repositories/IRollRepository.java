package edu.greenriver.sdev.dicerollingapi.repositories;

import edu.greenriver.sdev.dicerollingapi.model.Roll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRollRepository extends JpaRepository<Roll, Integer>
{
}
