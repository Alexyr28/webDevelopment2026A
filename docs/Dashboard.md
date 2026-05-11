Prerrequisitos Previos
1. PostgreSQL en ejecución con base de datos activities_db (usuario postgres, password 12345678), ya configurado según CLAUDE.md.
2. Confirmar que el proyecto activities usa paquetes bajo com.example.activities (convención estándar de Spring Initializr).
---
Paso 1: Crear Entidades Faltantes (Productos y Ventas)
El mock requiere datos de ventas y productos. Estas entidades no existen en el proyecto base, así que se crearán siguiendo el patrón de las entidades existentes (Lombok, JPA):
1.1 Enum SaleStatus (Estados de venta)
Ruta: src/main/java/com/example/activities/entity/SaleStatus.java
package com.example.activities.entity;
public enum SaleStatus {
    COMPLETED, PENDING, CANCELLED
}
1.2 Entidad Product
Ruta: src/main/java/com/example/activities/entity/Product.java
package com.example.activities.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer stock;
}
1.3 Entidad Sale (Venta)
Ruta: src/main/java/com/example/activities/entity/Sale.java
package com.example.activities.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "sales")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime saleDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SaleStatus status;
    @Column(nullable = false)
    private Double total;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleItem> items;
}
1.4 Entidad SaleItem (Detalle de venta)
Ruta: src/main/java/com/example/activities/entity/SaleItem.java
package com.example.activities.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "sale_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(nullable = false)
    private Integer quantity;
}
---
Paso 2: Crear Repositorios JPA
Siguiendo el patrón de Spring Data JPA del proyecto:
2.1 ProductRepository
Ruta: src/main/java/com/example/activities/repository/ProductRepository.java
package com.example.activities.repository;
import com.example.activities.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
2.2 SaleRepository
Ruta: src/main/java/com/example/activities/repository/SaleRepository.java
package com.example.activities.repository;
import com.example.activities.entity.Sale;
import com.example.activities.entity.SaleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    // Conteo por estado (para completed/pending/cancelled)
    long countByStatus(SaleStatus status);
    
    // Ventas en un rango de fechas (para mensual/semanal)
    List<Sale> findBySaleDateBetween(LocalDateTime start, LocalDateTime end);
}
2.3 SaleItemRepository
Ruta: src/main/java/com/example/activities/repository/SaleItemRepository.java
package com.example.activities.repository;
import com.example.activities.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
    // Items de ventas completadas (para top productos)
    List<SaleItem> findBySale_Status(SaleStatus status);
}
---
Paso 3: Crear DTO de Respuesta
Estructura idéntica al mock, siguiendo el patrón de DTOs separados (request/response) del proyecto:
Ruta: src/main/java/com/example/activities/dto/response/DashboardSummaryResponse.java
package com.example.activities.dto.response;
import lombok.*;
import java.util.List;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DashboardSummaryResponse {
    private Long completed;
    private Long pending;
    private Long cancelled;
    private List<MonthlySaleDTO> monthlySales;
    private List<TopProductDTO> topProducts;
    private List<WeeklySaleDTO> weeklySales;
    // DTO anidado para ventas mensuales
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class MonthlySaleDTO {
        private String month;
        private Double total;
    }
    // DTO anidado para top productos
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class TopProductDTO {
        private String name;
        private Long total;
    }
    // DTO anidado para ventas semanales
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class WeeklySaleDTO {
        private String week;
        private Double total;
    }
}
---
Paso 4: Crear Servicio DashboardService
Sigue el patrón de service (interfaz + impl) del proyecto:
4.1 Interfaz DashboardService
Ruta: src/main/java/com/example/activities/service/DashboardService.java
package com.example.activities.service;
import com.example.activities.dto.response.DashboardSummaryResponse;
public interface DashboardService {
    DashboardSummaryResponse getDashboardSummary();
}
4.2 Implementación DashboardServiceImpl
Ruta: src/main/java/com/example/activities/service/impl/DashboardServiceImpl.java
package com.example.activities.service.impl;
import com.example.activities.dto.response.DashboardSummaryResponse;
import com.example.activities.entity.SaleStatus;
import com.example.activities.repository.SaleRepository;
import com.example.activities.repository.SaleItemRepository;
import com.example.activities.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    @Override
    public DashboardSummaryResponse getDashboardSummary() {
        // 1. Conteos de ventas por estado (coincide con mock: 120 completed, 45 pending, 10 cancelled)
        Long completed = saleRepository.countByStatus(SaleStatus.COMPLETED);
        Long pending = saleRepository.countByStatus(SaleStatus.PENDING);
        Long cancelled = saleRepository.countByStatus(SaleStatus.CANCELLED);
        // 2. Ventas mensuales (Enero a Mayo 2026, igual que el mock)
        List<DashboardSummaryResponse.MonthlySaleDTO> monthlySales = new ArrayList<>();
        int currentYear = 2026;
        for (Month month : List.of(Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY)) {
            LocalDateTime start = LocalDateTime.of(currentYear, month, 1, 0, 0);
            LocalDateTime end = start.plusMonths(1).minusNanos(1);
            Double total = saleRepository.findBySaleDateBetween(start, end).stream()
                    .filter(s -> s.getStatus() == SaleStatus.COMPLETED)
                    .mapToDouble(s -> s.getTotal())
                    .sum();
            monthlySales.add(DashboardSummaryResponse.MonthlySaleDTO.builder()
                    .month(month.getDisplayName(TextStyle.FULL, new Locale("es", "ES")))
                    .total(total)
                    .build());
        }
        // 3. Top productos (por cantidad vendida en ventas completadas)
        List<DashboardSummaryResponse.TopProductDTO> topProducts = saleItemRepository
                .findBySale_Status(SaleStatus.COMPLETED).stream()
                .collect(Collectors.groupingBy(si -> si.getProduct().getName(),
                        Collectors.summingInt(SaleItem::getQuantity)))
                .entrySet().stream()
                .map(entry -> DashboardSummaryResponse.TopProductDTO.builder()
                        .name(entry.getKey())
                        .total((long) entry.getValue())
                        .build())
                .sorted((a, b) -> b.getTotal().compareTo(a.getTotal()))
                .limit(4)
                .toList();
        // 4. Ventas semanales (últimas 4 semanas, igual que el mock)
        List<DashboardSummaryResponse.WeeklySaleDTO> weeklySales = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < 4; i++) {
            LocalDateTime start = now.minusWeeks(i).with(java.time.DayOfWeek.MONDAY).toLocalDate().atStartOfDay();
            LocalDateTime end = start.plusWeeks(1).minusNanos(1);
            Double total = saleRepository.findBySaleDateBetween(start, end).stream()
                    .filter(s -> s.getStatus() == SaleStatus.COMPLETED)
                    .mapToDouble(s -> s.getTotal())
                    .sum();
            weeklySales.add(DashboardSummaryResponse.WeeklySaleDTO.builder()
                    .week("Semana " + (i + 1))
                    .total(total)
                    .build());
        }
        return DashboardSummaryResponse.builder()
                .completed(completed)
                .pending(pending)
                .cancelled(cancelled)
                .monthlySales(monthlySales)
                .topProducts(topProducts)
                .weeklySales(weeklySales)
                .build();
    }
}
---
Paso 5: Crear Controlador DashboardController
Sigue el patrón de controladores existentes (ej. ActivityController):
Ruta: src/main/java/com/example/activities/controller/DashboardController.java
package com.example.activities.controller;
import com.example.activities.dto.response.DashboardSummaryResponse;
import com.example.activities.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;
    @GetMapping
    public DashboardSummaryResponse getDashboard() {
        return dashboardService.getDashboardSummary();
    }
}
---
Paso 6: Poblar Datos de Prueba (Opcional)
Para que el endpoint retorne datos idénticos al mock, crear un cargador de datos inicial:
Ruta: src/main/java/com/example/activities/config/DataLoader.java
package com.example.activities.config;
import com.example.activities.entity.*;
import com.example.activities.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final ProductRepository productRepo;
    private final SaleRepository saleRepo;
    private final SaleItemRepository saleItemRepo;
    @Override
    public void run(String... args) {
        if (productRepo.count() == 0) {
            // Crear productos del mock
            Product laptop = Product.builder().name("Laptop").price(1200.0).stock(10).build();
            Product mouse = Product.builder().name("Mouse").price(25.0).stock(50).build();
            Product teclado = Product.builder().name("Teclado").price(75.0).stock(30).build();
            Product monitor = Product.builder().name("Monitor").price(300.0).stock(20).build();
            productRepo.saveAll(List.of(laptop, mouse, teclado, monitor));
            // Crear ventas para enero (total 1200)
            Sale saleEnero = Sale.builder()
                    .saleDate(LocalDateTime.of(2026, 1, 15, 10, 0))
                    .status(SaleStatus.COMPLETED)
                    .total(1200.0)
                    .build();
            saleRepo.save(saleEnero);
            saleItemRepo.save(SaleItem.builder().sale(saleEnero).product(laptop).quantity(1).build());
            // Crear ventas para febrero (total 1800)
            Sale saleFebrero = Sale.builder()
                    .saleDate(LocalDateTime.of(2026, 2, 20, 11, 0))
                    .status(SaleStatus.COMPLETED)
                    .total(1800.0)
                    .build();
            saleRepo.save(saleFebrero);
            saleItemRepo.saveAll(List.of(
                    SaleItem.builder().sale(saleFebrero).product(laptop).quantity(1).build(),
                    SaleItem.builder().sale(saleFebrero).product(monitor).quantity(1).build()
            ));
            // Repetir para marzo, abril, mayo y semanas para coincidir con el mock
            // (Ajustar totales de ventas para que coincidan con dashboard.mock.ts)
        }
    }
}
---
## Paso 7: Verificación Final
1. Ejecutar backend: `cd activities && ./mvnw spring-boot:run`
2. Probar endpoint: Visitar `http://localhost:8080/api/dashboard` o usar `curl`
3. Confirmar que el JSON retornado coincide con la estructura de `dashboard.mock.ts`
4. El frontend `minipos-web` ya está configurado para consumir este endpoint (cambios previos en `dashboard.ts`)
---
Notas
- Si el proyecto ya tiene una entidad Activity con campo status, se puede reutilizar para los conteos de completed/pending/cancelled en lugar de SaleStatus.
- Usa ddl-auto=update ya configurado, así que las nuevas tablas se crearán automáticamente en PostgreSQL.
- Ajustar los totales en DataLoader para que coincidan exactamente con los valores del mock (120 completed, 45 pending, 10 cancelled, etc.).