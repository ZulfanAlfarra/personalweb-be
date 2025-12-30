package com.zulfan.personal_web.controllers;

import com.zulfan.personal_web.entities.Transaction;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditController {
    private final EntityManagerFactory entityManagerFactory;

    @GetMapping("/transaction/{id}")
    public List<Transaction> getTransactionRevision(@PathVariable Long id){
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<Number> revisions = auditReader.getRevisions(Transaction.class, id);

        return revisions
                .stream()
                .map(revisionNumber -> auditReader.find(Transaction.class, id, revisionNumber))
                .collect(Collectors.toList());
    }

}
