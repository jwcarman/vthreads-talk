package com.callibrity.vthreads.olympics.medals;

import com.callibrity.vthreads.olympics.value.Country;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medals")
public class MedalsResource {

    private final Set<MedalResultsProvider> medalProviders;

    public MedalsResource(Set<MedalResultsProvider> medalProviders) {
        this.medalProviders = medalProviders;
    }

    @GetMapping("/standings")
    List<MedalStandings> getStandings() {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            final List<Supplier<Set<MedalResult>>> suppliers = medalProviders.stream().map(provider -> scope.fork(provider::listAllMedalResults)).collect(Collectors.toList());

            scope.join().throwIfFailed(t -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get medal results from all venues.", t));

            final Map<Country, List<MedalResult>> medalsByCountry = suppliers.stream().flatMap(supplier -> supplier.get().stream())
                    .collect(Collectors.groupingBy(MedalResult::country));
            return medalsByCountry.keySet().stream().map(country ->
                            medalsByCountry.get(country).stream().reduce(MedalStandings.blank(country), MedalStandings::add, (_, right) -> right))
                    .sorted(Comparator.comparing(MedalStandings::total).reversed())
                    .toList();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get medal results from all venues.", e);
        }
    }

    @GetMapping
    List<MedalResult> listAllMedals() {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            final List<Supplier<Set<MedalResult>>> suppliers = medalProviders.stream().map(provider -> scope.fork(provider::listAllMedalResults)).collect(Collectors.toList());
            scope.join().throwIfFailed(t -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get medal results from all venues.", t));

            return suppliers.stream().flatMap(supplier -> supplier.get().stream())
                    .sorted(Comparator.comparing(MedalResult::sport)
                            .thenComparing(MedalResult::medal))
                    .toList();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get medal standings from all venues.", e);
        }
    }
}
