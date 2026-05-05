package mk.ukim.finki.emt.emt_lab_backend.web.controller;

import mk.ukim.finki.emt.emt_lab_backend.model.dto.CreateHostDTO;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.DisplayHostDTO;
import mk.ukim.finki.emt.emt_lab_backend.model.projection.HostCountryProjection;
import mk.ukim.finki.emt.emt_lab_backend.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostController {
    private final HostApplicationService hostApplicationService;

    public HostController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayHostDTO>> getAllHosts() {
        return ResponseEntity.ok(hostApplicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDTO> getHostById(@PathVariable Long id) {
        return hostApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addHost")
    public ResponseEntity<DisplayHostDTO> createHost(@RequestBody CreateHostDTO createHostDTO) {
        return ResponseEntity.ok(hostApplicationService.create(createHostDTO));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDTO> updateHost(@PathVariable Long id,
                                                     @RequestBody CreateHostDTO createHostDTO) {
        return hostApplicationService
                .update(id, createHostDTO)
                .map(it -> ResponseEntity.ok().body(it))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteHost/{id}")
    public ResponseEntity<?> deleteHost(@PathVariable Long id) {
        return hostApplicationService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/stats")
    public ResponseEntity<List<HostCountryProjection>> getHostCountPerCountry() {
        return ResponseEntity.ok(hostApplicationService.getHostCountPerCountry());
    }
}
