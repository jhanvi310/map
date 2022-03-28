package com.flatmap.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.junit.Test;

public class FlattenJson {
  String json = "{\r\n"
  		+ "	\"resourceType\": \"CarePlan\",\r\n"
  		+ "	\"id\": \"gpvisit\",\r\n"
  		+ "	\"text\": {\r\n"
  		+ "		\"status\": \"additional\",\r\n"
  		+ "		\"div\": \"\"\r\n"
  		+ "	},\r\n"
  		+ "	\"contained\": [{\r\n"
  		+ "			\"resourceType\": \"Condition\",\r\n"
  		+ "			\"id\": \"p1\",\r\n"
  		+ "			\"clinicalStatus\": {\r\n"
  		+ "				\"coding\": [{\r\n"
  		+ "					\"system\": \"http://terminology.hl7.org/CodeSystem/condition-clinical\",\r\n"
  		+ "					\"code\": \"active\"\r\n"
  		+ "				}]\r\n"
  		+ "			},\r\n"
  		+ "			\"verificationStatus\": {\r\n"
  		+ "				\"coding\": [{\r\n"
  		+ "					\"system\": \"http://terminology.hl7.org/CodeSystem/condition-ver-status\",\r\n"
  		+ "					\"code\": \"confirmed\"\r\n"
  		+ "				}]\r\n"
  		+ "			},\r\n"
  		+ "			\"code\": {\r\n"
  		+ "				\"text\": \"Overseas encounter\"\r\n"
  		+ "			},\r\n"
  		+ "			\"subject\": {\r\n"
  		+ "				\"reference\": \"Patient/100\",\r\n"
  		+ "				\"display\": \"Peter James Chalmers\"\r\n"
  		+ "			}\r\n"
  		+ "		},\r\n"
  		+ "		{\r\n"
  		+ "			\"resourceType\": \"CareTeam\",\r\n"
  		+ "			\"id\": \"careteam\",\r\n"
  		+ "			\"participant\": [{\r\n"
  		+ "					\"id\": \"part1\",\r\n"
  		+ "					\"role\": [{\r\n"
  		+ "						\"coding\": [{\r\n"
  		+ "							\"system\": \"http://example.org/local\",\r\n"
  		+ "							\"code\": \"nur\"\r\n"
  		+ "						}],\r\n"
  		+ "						\"text\": \"nurse\"\r\n"
  		+ "					}],\r\n"
  		+ "					\"member\": {\r\n"
  		+ "						\"reference\": \"Practitioner/13\",\r\n"
  		+ "						\"display\": \"Nurse Nancy\"\r\n"
  		+ "					}\r\n"
  		+ "				},\r\n"
  		+ "				{\r\n"
  		+ "					\"id\": \"part2\",\r\n"
  		+ "					\"role\": [{\r\n"
  		+ "						\"coding\": [{\r\n"
  		+ "							\"system\": \"http://example.org/local\",\r\n"
  		+ "							\"code\": \"doc\"\r\n"
  		+ "						}],\r\n"
  		+ "						\"text\": \"doctor\"\r\n"
  		+ "					}],\r\n"
  		+ "					\"member\": {\r\n"
  		+ "						\"reference\": \"Practitioner/14\",\r\n"
  		+ "						\"display\": \"Doctor Dave\"\r\n"
  		+ "					}\r\n"
  		+ "				}\r\n"
  		+ "			]\r\n"
  		+ "		},\r\n"
  		+ "		{\r\n"
  		+ "			\"resourceType\": \"Goal\",\r\n"
  		+ "			\"id\": \"goal\",\r\n"
  		+ "			\"lifecycleStatus\": \"planned\",\r\n"
  		+ "			\"description\": {\r\n"
  		+ "				\"text\": \"Complete consultation\"\r\n"
  		+ "			},\r\n"
  		+ "			\"subject\": {\r\n"
  		+ "				\"reference\": \"Patient/100\",\r\n"
  		+ "				\"display\": \"Peter James Chalmers\"\r\n"
  		+ "			}\r\n"
  		+ "		}\r\n"
  		+ "	],\r\n"
  		+ "	\"status\": \"active\",\r\n"
  		+ "	\"intent\": \"plan\",\r\n"
  		+ "	\"subject\": {\r\n"
  		+ "		\"reference\": \"Patient/100\",\r\n"
  		+ "		\"display\": \"Peter James Chalmers\"\r\n"
  		+ "	},\r\n"
  		+ "	\"period\": {\r\n"
  		+ "		\"start\": \"2013-01-01T10:30:00+00:00\"\r\n"
  		+ "	},\r\n"
  		+ "	\"careTeam\": [{\r\n"
  		+ "		\"reference\": \"#careteam\"\r\n"
  		+ "	}],\r\n"
  		+ "	\"addresses\": [{\r\n"
  		+ "		\"reference\": \"#p1\",\r\n"
  		+ "		\"display\": \"obesity\"\r\n"
  		+ "	}],\r\n"
  		+ "	\"goal\": [{\r\n"
  		+ "		\"reference\": \"#goal\"\r\n"
  		+ "	}],\r\n"
  		+ "	\"activity\": [{\r\n"
  		+ "			\"outcomeReference\": [{\r\n"
  		+ "				\"reference\": \"Encounter/example\"\r\n"
  		+ "			}],\r\n"
  		+ "			\"detail\": {\r\n"
  		+ "				\"kind\": \"Appointment\",\r\n"
  		+ "				\"code\": {\r\n"
  		+ "					\"coding\": [{\r\n"
  		+ "						\"system\": \"http://example.org/local\",\r\n"
  		+ "						\"code\": \"nursecon\"\r\n"
  		+ "					}],\r\n"
  		+ "					\"text\": \"Nurse Consultation\"\r\n"
  		+ "				},\r\n"
  		+ "				\"status\": \"completed\",\r\n"
  		+ "				\"doNotPerform\": false,\r\n"
  		+ "				\"scheduledPeriod\": {\r\n"
  		+ "					\"start\": \"2013-01-01T10:38:00+00:00\",\r\n"
  		+ "					\"end\": \"2013-01-01T10:50:00+00:00\"\r\n"
  		+ "				},\r\n"
  		+ "				\"performer\": [{\r\n"
  		+ "					\"reference\": \"Practitioner/13\",\r\n"
  		+ "					\"display\": \"Nurse Nancy\"\r\n"
  		+ "				}]\r\n"
  		+ "			}\r\n"
  		+ "		},\r\n"
  		+ "		{\r\n"
  		+ "			\"detail\": {\r\n"
  		+ "				\"kind\": \"Appointment\",\r\n"
  		+ "				\"code\": {\r\n"
  		+ "					\"coding\": [{\r\n"
  		+ "						\"system\": \"http://example.org/local\",\r\n"
  		+ "						\"code\": \"doccon\"\r\n"
  		+ "					}],\r\n"
  		+ "					\"text\": \"Doctor Consultation\"\r\n"
  		+ "				},\r\n"
  		+ "				\"status\": \"scheduled\",\r\n"
  		+ "				\"doNotPerform\": false,\r\n"
  		+ "				\"performer\": [{\r\n"
  		+ "					\"reference\": \"Practitioner/14\",\r\n"
  		+ "					\"display\": \"Doctor Dave\"\r\n"
  		+ "				}]\r\n"
  		+ "			}\r\n"
  		+ "		}\r\n"
  		+ "	]\r\n"
  		+ "}";

  @Test
  public void testCreatingKeyValues() {
    Map<String, String> map = new HashMap<String, String>();
    try {
      addKeys("", new ObjectMapper().readTree(json), map);
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(map);
  }

  private void addKeys(String currentPath, JsonNode jsonNode, Map<String, String> map) {
    if (jsonNode.isObject()) {
      ObjectNode objectNode = (ObjectNode) jsonNode;
      Iterator<Map.Entry<String, JsonNode>> iter = objectNode.fields();
      String pathPrefix = currentPath.isEmpty() ? "" : currentPath + ".";

      while (iter.hasNext()) {
        Map.Entry<String, JsonNode> entry = iter.next();
        addKeys(pathPrefix + entry.getKey(), entry.getValue(), map);
      }
    } else if (jsonNode.isArray()) {
      ArrayNode arrayNode = (ArrayNode) jsonNode;
      for (int i = 0; i < arrayNode.size(); i++) {
        addKeys(currentPath + "[" + i + "]", arrayNode.get(i), map);
      }
    } else if (jsonNode.isValueNode()) {
      ValueNode valueNode = (ValueNode) jsonNode;
      map.put(currentPath, valueNode.asText());
    }
  }
}