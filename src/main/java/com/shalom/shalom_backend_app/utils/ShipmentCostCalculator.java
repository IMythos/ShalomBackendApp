package com.shalom.shalom_backend_app.utils;

import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;

public class ShipmentCostCalculator {
    public static double calculateTotalCost(Shipment shipment) {
        double totalCost = 0.0;

        if (shipment.getRoute() != null && shipment.getRoute().getDistanceKm() != null) {
            totalCost += shipment.getRoute().getDistanceKm() * 0.2;
        }

        if (shipment.getPkg() != null && shipment.getPkg().getWeight() != null) {
            totalCost += shipment.getPkg().getWeight() * 0.5;
        }

        if (shipment.getService() != null && shipment.getService().getBaseCost() != null) {
            totalCost += shipment.getService().getBaseCost();
        }

        return totalCost;
    }
}
