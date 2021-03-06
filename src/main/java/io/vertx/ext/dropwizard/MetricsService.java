/*
 * Copyright (c) 2011-2014 The original author or authors
 * ------------------------------------------------------
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 *
 *     The Eclipse Public License is available at
 *     http://www.eclipse.org/legal/epl-v10.html
 *
 *     The Apache License v2.0 is available at
 *     http://www.opensource.org/licenses/apache2.0.php
 *
 * You may elect to redistribute this code under either of these licenses.
 */

package io.vertx.ext.dropwizard;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.metrics.Measured;
import io.vertx.ext.dropwizard.impl.MetricsServiceImpl;

import java.util.Map;

/**
 * The metrics service mainly allows to return a snapshot of measured objects.
 *
 * @author <a href="mailto:nscavell@redhat.com">Nick Scavelli</a>
 */
@VertxGen
public interface MetricsService {

  /**
   * Creates a metric service for a given {@link io.vertx.core.Vertx} instance.
   *
   * @param vertx the vertx instance
   * @return the metrics service
   */
  public static MetricsService create(Vertx vertx) {
    // We don't use Vertx instance for now but we might later
    return new MetricsServiceImpl();
  }

  /**
   * Will return the metrics that correspond with this measured object, null if no metrics is available.<p/>
   *
   * Note: in the case of scaled servers, the JsonObject returns an aggregation of the metrics as the
   * dropwizard backend reports to a single server.
   *
   * @return the map of metrics where the key is the name of the metric (excluding the base name) and the value is
   * the json data representing that metric
   */
  JsonObject getMetricsSnapshot(Measured o);

  /**
   * @param measured the measure object
   * @return the base name of the measured object
   */
  String getBaseName(Measured measured);

}
