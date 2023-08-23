package simulationProcess;

import entity.EntityRenderingRules;
import map.MapClass;
import map.MapRenderer;

public class SimulationProcess {

    private final MapClass map;

    private final MapRenderer renderer = new MapRenderer();

    private final EntityRenderingRules rules = new EntityRenderingRules();


    public SimulationProcess(MapClass map) {
        this.map = map;
    }

    public void simulationLoop() {
        //этот метод должен вернуть MapClass с уже стоящими на местах Entity
         MapClass mapWithRules = rules.satisfyingRules(this.map);
         renderer.render(mapWithRules);
    }
}
