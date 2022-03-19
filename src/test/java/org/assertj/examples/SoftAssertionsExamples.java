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

import org.assertj.core.api.SoftAssertionError;
import org.assertj.core.api.SoftAssertions;
import org.assertj.examples.data.Mansion;
import org.junit.Test;

/**
 * 数字的断言示例。
 */
public class SoftAssertionsExamples extends AbstractAssertionsExamples {

  @Test
  public void host_dinner_party_where_nobody_dies() {
    Mansion mansion = new Mansion();
    mansion.hostPotentiallyMurderousDinnerParty();
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(mansion.guests())
        .as("Living Guests").isEqualTo(7);
    softly.assertThat(mansion.kitchen())
        .as("Kitchen").isEqualTo("clean");
    softly.assertThat(mansion.library())
        .as("Library").isEqualTo("clean");
    softly.assertThat(mansion.revolverAmmo())
        .as("Revolver Ammo").isEqualTo(6);
    softly.assertThat(mansion.candlestick())
        .as("Candlestick").isEqualTo("pristine");
    softly.assertThat(mansion.colonel())
        .as("Colonel").isEqualTo("well kempt");
    softly.assertThat(mansion.professor())
        .as("Professor").isEqualTo("well kempt");
    try {
      softly.assertAll();
    } catch (SoftAssertionError e) {
      logAssertionErrorMessage("SoftAssertion errors example", e);
    }
  }

}
