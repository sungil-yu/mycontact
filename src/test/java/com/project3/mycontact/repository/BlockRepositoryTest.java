package com.project3.mycontact.repository;

import com.project3.mycontact.domain.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BlockRepositoryTest {

    @Autowired
    BlockRepository blockRepository;

    @Test
    void crud(){

        Block block = new Block();
        block.setName("martin");
        block.setReason("친하지않음");
        block.setStartDate(LocalDate.now());
        block.setEndDate(LocalDate.now());

        blockRepository.save(block);
        List<Block> blocks = blockRepository.findAll();

        System.out.println(blocks);

        assertThat(blocks.size()).isEqualTo(3);


    }
}