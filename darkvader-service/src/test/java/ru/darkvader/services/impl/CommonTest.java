package ru.darkvader.services.impl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Khairullin on 22.08.2014.
 *
 * @author Khairullin
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ru.darkvader.config.TestConfig.class})
@Transactional
public abstract class CommonTest {
}
