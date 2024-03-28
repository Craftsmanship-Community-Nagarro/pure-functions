package com.ythirion

import org.scalatest.flatspec.AnyFlatSpec

import java.time.LocalDate

class rental_calculator_should extends AnyFlatSpec {
  "Calculate rentals on a list" should "produce a formatted statement" in {
    val rentals = List(
      new Rental(LocalDate.of(2020, 10, 9), "Le Refuge des Loups (LA BRESSE)", 1089.90),
      new Rental(LocalDate.of(2020, 10, 12), "Au pied de la Tour (NOUILLORC)", 1276.45),
      new Rental(LocalDate.of(2020, 10, 24), "Le moulin du bonheur (GLANDAGE)", 670.89),
    )
    val rentalCalculator = new RentalCalculator(rentals)
    val statement = rentalCalculator.calculateRental

    assert(rentalCalculator.calculated)
    assert(3037.24 == BigDecimal(rentalCalculator.amount).setScale(2, BigDecimal.RoundingMode.HALF_UP))
    assert(statement ==
      """2020-10-09 : Le Refuge des Loups (LA BRESSE) | 1089.90
2020-10-12 : Au pied de la Tour (NOUILLORC) | 1276.45
2020-10-24 : Le moulin du bonheur (GLANDAGE) | 670.89
Total amount | 3037.24""")
  }

  it should "produce IllegalStateException when rentals is empty" in {
    assertThrows[IllegalStateException] {
      new RentalCalculator(Nil).calculateRental
    }
  }
}