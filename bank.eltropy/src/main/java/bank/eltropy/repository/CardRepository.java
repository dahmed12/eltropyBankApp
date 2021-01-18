package bank.eltropy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.eltropy.domain.card.Card;


public interface CardRepository extends JpaRepository<Card, Long> {
}
