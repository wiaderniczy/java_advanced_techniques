package com.example.lab06.restControllers;

import com.example.lab06.models.Client;
import com.example.lab06.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientRepository clientRepo;

    public ClientController(ClientRepository clientRepo){
        this.clientRepo = clientRepo;
    }

    @GetMapping
    public List<Client> getAllClients(){
        return (List<Client>) clientRepo.findAll();
    }

    @PostMapping
    public Client createClient(@RequestBody Client client){
        return clientRepo.save(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id){
        Optional<Client> optionalClient = clientRepo.findById(id);
        if (optionalClient.isPresent()) {
            return ResponseEntity.ok(optionalClient.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody Client updatedClient) {
        Optional<Client> optionalClient = clientRepo.findById(id);
        if (optionalClient.isPresent()) {
            Client user = optionalClient.get();

            clientRepo.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
        Optional<Client> optionalClient = clientRepo.findById(id);
        if (optionalClient.isPresent()) {
            clientRepo.delete(optionalClient.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
