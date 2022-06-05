precision mediump float;
const vec3 c_ambientColor = vec3(0.1, 0.1, 0.6);
const vec3 c_diffuseColor = vec3(0.9, 0.9, 0.9);
const vec3 c_specularColor = vec3(1.0, 1.0, 1.0);
const vec3 c_LightPos = vec3(0.0, 0.0, -50.0);
const float specular_exp = 4.0;
const vec4 c_Color = vec4( 0.5, 0.5, 0.5, 0.5);

uniform sampler2D u_Texture;
varying vec3 v_Position;
varying vec2 v_TexCoordinate;
varying vec3 v_Normal;


void main()
{
        // ver 1
        float distance = 0.5 * length( v_Position);
        vec3 lightVector = normalize( -v_Position);
        float diffuse = max(dot(v_Normal, lightVector), 0.1);
        diffuse = diffuse * (1.0 / (1.0 + (0.25 * distance * distance)));
        diffuse = diffuse + 0.5;
        float specular = 4.0;
        // ver 2 - so so bad
//        vec3 lightPosNorm = normalize(c_LightPos);
//        vec3 cameraDir = normalize(-v_Position);
//        vec3 halfDir = normalize(lightPosNorm + cameraDir);
//        float specular = pow(max(dot(halfDir, v_Normal), 0.0), specular_exp);
//        specular = 1.0;
//        float diffuse = max(dot(lightPosNorm, v_Normal), 0.0);

        vec4 m_Texture = texture2D(u_Texture, v_TexCoordinate);
        if(  m_Texture.x == 0.0 && m_Texture.y == 0.0 && m_Texture.y == 0.0  ){
                vec4 m_color = c_Color*vec4(c_ambientColor * (1.0 - diffuse) + c_diffuseColor * (diffuse - specular) + c_specularColor * specular, 1.0);
                clamp(m_color, 0.0, 1.0);
                gl_FragColor = m_color;//* diffuse ;
        }else {
                vec4 m_color = texture2D(u_Texture, v_TexCoordinate)*vec4(c_ambientColor * (1.0 - diffuse) + c_diffuseColor * (diffuse - specular) + c_specularColor * specular, 1.0);
                clamp(m_color, 0.0, 1.0);
                gl_FragColor = m_color;//* diffuse ;
        }

}

// rotaion lamp
//        float distance = 0.3*length(u_LightPos - v_Position);
//        vec3 lightVector = normalize(u_LightPos - v_Position);
//        float diffuse = max(dot(v_Normal, lightVector), 0.1);
//        diffuse = diffuse * (1.0 / (1.0 + (0.25 * distance * distance)));
//        diffuse = diffuse + 0.3;
//         color + diffuse illumination level + texture properties
//        gl_FragColor = (v_Color * diffuse * texture2D(u_Texture, v_TexCoordinate));
