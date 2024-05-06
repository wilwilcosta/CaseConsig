package brcom.GatewayCaseConsignado.Gatewayms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceViewer {
    @Autowired
    private DiscoveryClient client;

    @GetMapping("/discovery")
    public ResponseEntity<?> test(@RequestParam String app_name) {
        return ResponseEntity.ok(getApplications(app_name));
    }


    public int getApplications(String appName) {
        List<ServiceInstance> instances = client.getInstances(appName);
        return instances.size();
    }
}
