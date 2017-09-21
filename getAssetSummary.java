public with sharing class getAssetSummary {

    public getAssetSummary(ApexPages.StandardController stdController) {

    }


    public Summary[] Summaries { get; set; }

    public getAssetSummary() {
//        AggregateResult[] results = [
//            SELECT Name, Count(Id) Quantity FROM Account WHERE Account.Name like 'N%' GROUP BY Name 
//        ];
        AggregateResult[] results = [
            SELECT Name, Count(Id) Quantity FROM Opportunity GROUP BY Name
        ];
        Summaries = new List<Summary>();
        for (AggregateResult ar : results) {
            Summaries.add(new Summary(ar));
        }
    }

    // wrapper class to hold aggregate data
    public class Summary {
        public Integer Quantity { get; private set; }
        public String Name { get; private set; }

        public Summary(AggregateResult ar) {
            Quantity = (Integer) ar.get('Quantity');
            Name = (String) ar.get('Name');
        }
    }
}