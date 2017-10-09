public enum AccountStatus {
  /**
   * Account is active
   */
  ACTIVE,
  /**
   * Account is frozen
   */
  FROZEN;

  @Override
  public String toString() {
    switch (this) {
      case ACTIVE:
        return "active";
      case FROZEN:
        return "frozen";
      default:
        return "default";
    }
  }
  
  
}
