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
import static org.assertj.core.api.Assertions.entry;
import static org.assertj.examples.data.Ring.manRing;
import static org.assertj.examples.data.Ring.narya;
import static org.assertj.examples.data.Ring.nenya;
import static org.assertj.examples.data.Ring.oneRing;

import org.junit.Test;

/**
 * Map assertions example.
 * 映射关系的断言示例。
 *
 * @author Joel Costigliola
 */
public class MapAssertionsExamples extends AbstractAssertionsExamples {

  @Test
  public void map_assertions_examples() {
    // ringBearers is a Map of TolkienCharacter indexed by the Ring they are wearing.
    assertThat(ringBearers)
        .isNotEmpty().hasSize(4);
    
    // note the usage of Assertions.entry(key, value) synthetic sugar for better readability (similar to MapEntry.entry(key, value)). 
    assertThat(ringBearers)
        .contains(entry(oneRing, frodo), entry(nenya, galadriel));
    // same assertion but different way of expressing it : no entry call needed but no varargs support. 
    assertThat(ringBearers)
        .containsEntry(oneRing, frodo)
        .containsEntry(nenya, galadriel);
    // opposite of contains/containsEntry
    assertThat(ringBearers)
        .doesNotContain(entry(oneRing, aragorn));
    assertThat(ringBearers)
        .doesNotContainEntry(oneRing, aragorn);
    
    // Assertion on key
    assertThat(ringBearers)
        .containsKey(nenya);
    assertThat(ringBearers)
        .containsKeys(nenya, narya);
    assertThat(ringBearers)
        .doesNotContainKey(manRing);
    
    // Assertion on value
    assertThat(ringBearers)
        .containsValue(frodo);
    assertThat(ringBearers)
        .doesNotContainValue(sam);
    
    ringBearers.clear();
    assertThat(ringBearers).isEmpty();
    assertThat(ringBearers).contains();
  }
  
}
