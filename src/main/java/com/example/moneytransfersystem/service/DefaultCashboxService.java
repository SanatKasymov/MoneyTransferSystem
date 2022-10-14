package com.example.moneytransfersystem.service;

import com.example.moneytransfersystem.domain.Cashbox;
import com.example.moneytransfersystem.exception.CashboxAlreadyExistsException;
import com.example.moneytransfersystem.exception.NotFoundException;
import com.example.moneytransfersystem.repository.CashboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultCashboxService implements CashboxService {

    private final CashboxRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultCashboxService(CashboxRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Cashbox create(String name, String password) {
        if (repository.existsByName(name)) throw new CashboxAlreadyExistsException(name);

        String encodedPassword = passwordEncoder.encode(password);
        Cashbox cashbox = new Cashbox(name, encodedPassword);

        repository.save(cashbox);

        return cashbox;
    }

    @Override
    @Transactional(readOnly = true)
    public Cashbox get(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cashbox> getAll() {
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByName(username);
    }

}
