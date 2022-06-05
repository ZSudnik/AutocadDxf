precision mediump float;

const vec3 c_ambientColor = vec3(0.1, 0.1, 0.6);
const vec3 c_diffuseColor = vec3(0.9, 0.9, 0.9);
const vec3 c_specularColor = vec3(1.0, 1.0, 1.0);
const vec3 c_LightPos = vec3(0.0, 0.0, -50.0);


varying vec3 v_Normal;
varying vec3 v_Position;
varying vec4 v_Color;


void main()
{
    vec3 lightPosNorm = normalize(c_LightPos);
    //        vec3 cameraDir = normalize(-v_Position);
    //        vec3 halfDir = normalize(lightPosNorm + cameraDir);
    //        float specular = pow(max(dot(halfDir, v_Normal), 0.0), specular_exp);
    float specular = 1.0;
    float diffuse = max(dot(lightPosNorm, v_Normal), 0.0);
    vec4 m_color = v_Color*vec4(c_ambientColor * (1.0 - diffuse) + c_diffuseColor * (diffuse - specular) + c_specularColor * specular, 1.0);
    clamp(m_color, 0.0, 1.0);
    gl_FragColor = m_color;
}


//
//    vec3 inverseLightDirection = normalize(vec3(0.0, 1.0, 1.0));
//
//    //        float distance = 0.3*length(u_LightPos - v_Position);
////        vec3 lightVector = normalize(u_LightPos - v_Position);
////        diffuse = diffuse * (1.0 / (1.0 + (0.25 * distance * distance)));
////        diffuse = diffuse + 0.3;
//    // color + diffuse illumination level + texture properties
//    //    gl_FragColor = (v_Color * diffuse * texture2D(u_Texture, v_TexCoordinate));
//
//    //        float diffuse = max(dot(transformedVertexNormal, inverseLightDirection), 0.1);
////    gl_FragColor = a_Color*diffuse ;
//
//    /* [Setup scene vectors.] */
////    vec3 transformedVertexNormal = normalize((u_MVMatrix * vec4(a_Normal, 0.0)).xyz);
////    vec3 inverseLightDirection = normalize(vec3(0.0, 1.0, 1.0));
//    vec3 fragColour = vec3(0.0);
//    /* [Setup scene vectors.] */
//    /* [Calculate the diffuse component.] */
//    vec3 diffuseLightIntensity = vec3(1.0, 1.0, 1.0);
//    vec3 vertexDiffuseReflectionConstant = a_Color.xyz;
//    float normalDotLight = max(0.1, dot(transformedVertexNormal, inverseLightDirection));
//    fragColour += normalDotLight * vertexDiffuseReflectionConstant * diffuseLightIntensity;
//    /* [Calculate the diffuse component.] */
//    /* [Calculate the ambient component.] */
//    vec3 ambientLightIntensity = vec3(0.7, 0.7, 0.7);
//    vec3 vertexAmbientReflectionConstant = a_Color.xyz;
//    fragColour += vertexAmbientReflectionConstant * ambientLightIntensity;
//    /* [Calculate the ambient component.] */
//    /* [Calculate the specular component.] */
//    vec3 inverseEyeDirection = normalize(vec3(0.0, 0.0, 1.0));
//    vec3 specularLightIntensity = vec3(1.0, 1.0, 1.0);
//    vec3 vertexSpecularReflectionConstant = vec3(1.0, 1.0, 1.0);
//    float shininess = 2.0;
//    vec3 lightReflectionDirection = reflect(vec3(0) - inverseLightDirection, transformedVertexNormal);
//    float normalDotReflection = max(0.0, dot(inverseEyeDirection, lightReflectionDirection));
//    fragColour += pow(normalDotReflection, shininess) * vertexSpecularReflectionConstant * specularLightIntensity;
//    /* [Calculate the specular component.] */
//    /* Make sure the fragment colour is between 0 and 1. */
//    clamp(fragColour, 0.0, 1.0);
//    gl_FragColor = vec4(fragColour, a_Color.z);