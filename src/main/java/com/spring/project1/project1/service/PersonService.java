package com.spring.project1.project1.service;

import com.spring.project1.project1.domain.Block;
import com.spring.project1.project1.domain.Person;
import com.spring.project1.project1.repository.BlockRepository;
import com.spring.project1.project1.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlocks(){
        List<Person> people = personRepository.findAll();
//        List<Block> blocks = blockRepository.findAll();
//        List<String> blockNames = blocks.stream().map(Block::getName).collect(Collectors.toList());
//        return people.stream().filter(person -> !blockNames.contains(person.getName())).collect(Collectors.toList());
        return people.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id){
        Person person = personRepository.findById(id).get();
//        System.out.println("person : "+person);
        log.info("person : {}", person);
        return person;
    }
}
