precision mediump float;

const vec3 c_ambientColor = vec3(0.1, 0.1, 0.6);
const vec3 c_diffuseColor = vec3(0.9, 0.9, 0.9);
const vec3 c_specularColor = vec3(1.0, 1.0, 1.0);
const vec3 c_LightPos = vec3(0.0, 0.0, -50.0);
//const float specular_exp = 4.0;

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
//    //////////////////////////////////////////////////////////////////
        //        vec3 u_LightPos = vec3( 0.0, 0.0, 0.5);
//        float distance = 0.3*length(u_LightPos - v_Position);
//        vec3 lightVector = normalize(u_LightPos - v_Position);
//        float diffuse = max(dot(v_Normal, lightVector), 0.1);
//        diffuse = diffuse * (1.0 / (1.0 + (0.25 * distance * distance)));
//        diffuse = diffuse + 0.3;
//        gl_FragColor = v_Color;
//        gl_FragColor = vec4( normalize(-v_Position), 1.0 )+v_Color+vec4( v_Normal, 1.0 ) +vec4( c_ambientColor, 1.0 )+vec4( c_diffuseColor, 1.0 )+vec4( c_LightPos, 1.0 )+vec4( c_specularColor, 1.0 );
}

