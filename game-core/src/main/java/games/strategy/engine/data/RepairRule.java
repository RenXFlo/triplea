package games.strategy.engine.data;

import static com.google.common.base.Preconditions.checkNotNull;

import games.strategy.util.IntegerMap;

/**
 * A repair rule.
 */
public class RepairRule extends DefaultNamed {
  private static final long serialVersionUID = -45646671022993959L;
  private final IntegerMap<Resource> m_cost;
  private final IntegerMap<NamedAttachable> m_results;

  public RepairRule(final String name, final GameData data) {
    this(name, data, new IntegerMap<>(), new IntegerMap<>());
  }

  public RepairRule(
      final String name,
      final GameData data,
      final IntegerMap<NamedAttachable> results,
      final IntegerMap<Resource> costs) {
    super(name, data);

    checkNotNull(results);
    checkNotNull(costs);

    m_cost = new IntegerMap<>(costs);
    m_results = new IntegerMap<>(results);
  }

  protected void addCost(final Resource resource, final int quantity) {
    m_cost.put(resource, quantity);
  }

  /**
   * Benefits must be a resource or a unit.
   */
  protected void addResult(final NamedAttachable obj, final int quantity) {
    if (!(obj instanceof UnitType) && !(obj instanceof Resource)) {
      throw new IllegalArgumentException("results must be units or resources, not:" + obj.getClass().getName());
    }
    m_results.put(obj, quantity);
  }

  public IntegerMap<Resource> getCosts() {
    return new IntegerMap<>(m_cost);
  }

  public IntegerMap<NamedAttachable> getResults() {
    return m_results;
  }

  @Override
  public String toString() {
    return "RepairRule:" + getName();
  }
}
