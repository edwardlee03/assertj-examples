/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2013 the original author or authors.
 */
package org.assertj.examples;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.filter.Filters.filter;
import static org.assertj.examples.data.Race.HOBBIT;
import static org.assertj.examples.data.Race.MAIA;
import static org.assertj.examples.data.Race.MAN;

import org.junit.Test;

import org.assertj.core.api.Condition;
import org.assertj.examples.data.BasketBallPlayer;

/**
 * Iterable (including Collection) assertions examples.<br>
 * 迭代器的断言示例。
 * 
 * @author Joel Costigliola
 */
public class FilterExamples extends AbstractAssertionsExamples {

  @Test
  public void filter_with_examples() {
    // with(property).equalsTo(someValue) works by instrospection on specified property
    assertThat(filter(fellowshipOfTheRing).with("race").equalsTo(HOBBIT).get())
        .containsOnly(sam, frodo, pippin, merry);
    // same thing - shorter way
    assertThat(filter(fellowshipOfTheRing).with("race", HOBBIT).get())
        .containsOnly(sam, frodo, pippin, merry);

    // nested property are supported
    assertThat(filter(fellowshipOfTheRing).with("race.name").equalsTo("Man").get())
        .containsOnly(aragorn, boromir);

    // you can apply different comparison
    assertThat(filter(fellowshipOfTheRing).with("race").notIn(HOBBIT, MAN).get())
        .containsOnly(gandalf, gimli, legolas);
    assertThat(filter(fellowshipOfTheRing).with("race").in(MAIA, MAN).get())
        .containsOnly(gandalf, boromir, aragorn);
    assertThat(filter(fellowshipOfTheRing).with("race").notEqualsTo(HOBBIT).get())
        .contains(gandalf, boromir, aragorn, gimli, legolas);
    // you can chain multiple filter criteria 
    assertThat(filter(fellowshipOfTheRing).with("race").equalsTo(MAN).and("name").notEqualsTo("Boromir").get())
        .contains(aragorn);
  }

  @Test
  public void filter_on_condition_examples() {
    // having(condition) example
    Condition<BasketBallPlayer> mvpStats= new Condition<BasketBallPlayer>() {
      @Override
      public boolean matches(BasketBallPlayer player) {
        return player.getPointsPerGame() > 20 && (player.getAssistsPerGame() >= 8 || player.getReboundsPerGame() >= 8);
      }
    };
    assertThat(filter(players).having(mvpStats).get())
        .containsOnly(rose, james);
    
    // being(condition) example : same condition can be applied but is renamed to be more readable
    Condition<BasketBallPlayer> potentialMvp= mvpStats;
    assertThat(filter(players).being(potentialMvp).get())
        .containsOnly(rose, james);
  }

}
