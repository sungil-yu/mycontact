package com.project3.mycontact.repository;

import com.project3.mycontact.domain.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class BlockRepositoryTest {

    @Autowired
    BlockRepository blockRepository;

    @Test
    void crud(){

        Block block = new Block();
        block.setName("martin");
        block.setReason("martin");
        block.setStartDate(LocalDate.now());
        block.setEndDate(LocalDate.now());

        blockRepository.save(block);
        List<Block> blocks = blockRepository.findAll();

        System.out.println(blocks);

        assertThat(blocks.size()).isEqualTo(1);
        assertThat(blocks.get(0).getName()).isEqualTo("martin");
        assertThat(blocks.get(0).getReason()).isEqualTo("친하지않음");


    }
}