precision mediump float;

const vec3 c_ambientColor = vec3(0.1, 0.1, 0.6);
const vec3 c_diffuseColor = vec3(0.9, 0.9, 0.9);
const vec3 c_specularColor = vec3(1.0, 1.0, 1.0);
const vec3 c_LightPos = vec3(0.0, 0.0, -50.0);
const float specular_exp = 32.0;
//Camera camera(glm::vec3(0.0f, 0.0f, 3.0f));
//const vec3 lightPos = vec3(1.2, 1.0, 2.0);
const vec3 lightPos = vec3(0.0, 0.0, 2.0);
const vec3 lightColor = vec3( 1.0, 1.0, 1.0);
const vec3 viewPos = vec3( 0.0, 0.0, 13.0);

varying vec3 v_Normal;
varying vec3 v_Position;
varying vec4 v_Color;



void main()
{

    // ambient
    float ambientStrength = 0.3;
    vec3 ambient = ambientStrength * lightColor;

    // diffuse
    vec3 norm = normalize(v_Normal);
    vec3 lightDir = normalize(lightPos - v_Position);
    float diff = max(dot(norm, lightDir), 0.0);
//    float distance = 0.25 * length( lightPos - v_Position);
//    diff = diff * (1.0 / (1.0 + (0.25 * distance )));
    vec3 diffuse = diff * lightColor;

    // specular
    float specularStrength = 0.3;
    vec3 viewDir = normalize(viewPos - v_Position);
    vec3 reflectDir = reflect(-lightDir, norm);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), 32.0);
    vec3 specular = specularStrength * spec * lightColor;

    vec3 result = (ambient + diffuse + specular) * v_Color.xyz;
    vec4 mColor = vec4(result, v_Color.w);
    clamp( mColor, 0.0, 1.0);
    gl_FragColor = mColor;

    // ver 1 - ambient and diffuse
//    float distance = 0.5 * length( v_Position);
//    vec3 lightVector = normalize( -v_Position);
//    float diffuse = max(dot(v_Normal, lightVector), 0.1);
//    diffuse = diffuse * (1.0 / (1.0 + (0.25 * distance * distance)));
//    diffuse = diffuse + 0.5;
//    float specular = 4.0;
//    vec4 m_color = v_Color*vec4(c_ambientColor * (1.0 - diffuse) + c_diffuseColor * (diffuse - specular) + c_specularColor * specular, 1.0);
//    clamp(m_color, 0.0, 1.0);
//    gl_FragColor = m_color;//* diffus

    //////////////////////////////////////
//    ver 2
    // ambient
//    float ambientStrength = 0.4;
//    vec3 ambient = ambientStrength * lightColor;
//
//    // diffuse
//    vec3 norm = normalize(v_Normal);
//    vec3 lightDir = normalize(lightPos - v_Position);
//    float diff = max(dot(norm, v_Position), 0.0);
//    vec3 diffuse = diff * lightColor;
//
//    vec3 result = (ambient + diffuse) * v_Color.xyz;
//    gl_FragColor = vec4(result, v_Color.w);
    //    //////////////////////////////////
//    ver 3
//    vec3 lightPosNorm = normalize(c_LightPos);
//    vec3 cameraDir = normalize(-v_Position);
//    vec3 halfDir = normalize(lightPosNorm + cameraDir);
//    float specular = pow(max(dot(halfDir, v_Normal), 0.0), specular_exp);
////    float specular = 1.0;
//    float diffuse = max(dot(lightPosNorm, v_Normal), 0.0);
//    vec3 mAmbient = c_ambientColor * (1.0 - diffuse);
//    vec3 mDiffuse =  c_diffuseColor * (diffuse - specular);
//    vec3 mSpecular = c_specularColor * specular;
//    vec4 m_color = v_Color*vec4( mSpecular+mDiffuse+mAmbient, 1.0);
////    vec4 m_color = v_Color*vec4(mAmbient + mDiffuse + mSpecular, 1.0);
//    clamp(m_color, 0.0, 1.0);
//    gl_FragColor = m_color;
////    gl_FragColor = v_Color;
}

//
//    // calculate MVP matrix
//    mat4 u_MVMatrix = u_VMatrix * u_MMatrix;
//    mat4 u_MVPMatrix = u_PMatrix * u_MVMatrix;
//
//    // calculate rendered position
//    gl_Position = u_MVPMatrix * a_Position;
//
//    // Transform the vertex into eye space.
//    vec3 modelVertex = vec3(u_MMatrix * a_Position);
//
//    // Transform the normal's orientation into eye space.
//    vec3 modelNormal = normalize(vec3(u_MMatrix * vec4(a_Normal, 0.0)));
//
//    // Get a lighting direction vector from the light to the vertex.
//    vec3 lightVector = normalize(u_LightPos - modelVertex);
//
//    // Calculate the dot product of the light vector and vertex normal. If the normal and light vector are
//    // pointing in the same direction then it will get max illumination.
//    // float diffuse = max(dot(lightVector, modelNormal),0.0); // --> lights only on camera in front of face
//    float diffuse = max(dot(lightVector, modelNormal),0.0);
//
//    // Attenuate the light based on distance.
//    float distance = distance(u_LightPos,modelVertex);
//    distance = 1.0 / (1.0 + distance * 0.05);
//    diffuse = diffuse * distance;
//
//    // specular light
//    vec3 viewDir = normalize(u_cameraPos - modelVertex);
//    vec3 reflectDir = reflect(-lightVector, modelNormal);
//    float specular = pow(max(dot(reflectDir, viewDir),0.0),32.0);
//
//    // ambient light
//    float ambient = 0.5;
//
//    // Multiply the color by the illumination level. It will be interpolated across the triangle.
//    v_Color = a_Color * min((diffuse + specular + ambient),1.0);
//    v_Color[3] = a_Color[3]; // correct alpha
//



//
//    vec3 inverseLightDirection = normalize(vec3(0.0, 1.0, 1.0));
//    /* [Setup scene vectors.] */
//    //    vec3 transformedVertexNormal = normalize((u_MVMatrix * vec4(a_Normal, 0.0)).xyz);
//    //    vec3 inverseLightDirection = normalize(vec3(0.0, 1.0, 1.0));
//    vec3 fragColour = vec3(0.0);
//    /* [Setup scene vectors.] */
//    /* [Calculate the diffuse component.] */
//    vec3 diffuseLightIntensity = vec3(1.0, 1.0, 1.0);
//    vec3 vertexDiffuseReflectionConstant = a_Color.xyz;
//    float normalDotLight = max(0.1, dot(v_Normal, inverseLightDirection));
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
//    vec3 lightReflectionDirection = reflect(vec3(0) - inverseLightDirection, v_Normal);
//    float normalDotReflection = max(0.0, dot(inverseEyeDirection, lightReflectionDirection));
//    fragColour += pow(normalDotReflection, shininess) * vertexSpecularReflectionConstant * specularLightIntensity;
//    /* [Calculate the specular component.] */
//    /* Make sure the fragment colour is between 0 and 1. */
//    clamp(fragColour, 0.0, 1.0);
//    gl_FragColor = vec4(fragColour, a_Color.z);
//