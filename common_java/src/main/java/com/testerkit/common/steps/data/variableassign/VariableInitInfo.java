package com.testerkit.common.steps.data.variableassign;

/**
 * @atuthor able
 */
public class VariableInitInfo {
    private VariableInitType type;

    private RandomInfo random;

    private ApiInfo api;

    private DbInfo db;

    private ResultFilterInfo resultFilter;


    public VariableInitType getType() {
        return type;
    }

    public void setType(VariableInitType type) {
        this.type = type;
    }

    public RandomInfo getRandom() {
        return random;
    }

    public void setRandom(RandomInfo random) {
        this.random = random;
    }

    public ApiInfo getApi() {
        return api;
    }

    public void setApi(ApiInfo api) {
        this.api = api;
    }

    public DbInfo getDb() {
        return db;
    }

    public void setDb(DbInfo db) {
        this.db = db;
    }

    public ResultFilterInfo getResultFilter() {
        return resultFilter;
    }

    public void setResultFilter(ResultFilterInfo resultFilter) {
        this.resultFilter = resultFilter;
    }


    @Override
    public String toString() {
        return "VaribaleInitInfo{" +
                "initType=" + type +
                ", random=" + random +
                ", api=" + api +
                ", db=" + db +
                ", resultFilter=" + resultFilter +
                '}';
    }
}
